package com.dailystudio.codegen.ksp.core.db

import android.graphics.drawable.Drawable
import android.view.View
import androidx.room.Insert
import androidx.room.Query
import com.dailystudio.codegen.ksp.core.R
import com.dailystudio.devbricksx.annotations.*
import com.dailystudio.devbricksx.annotations.data.DaoExtension
import com.dailystudio.devbricksx.annotations.data.RoomCompanion
import com.dailystudio.devbricksx.annotations.fragment.DataSource
import com.dailystudio.devbricksx.annotations.fragment.ListFragment
import com.dailystudio.devbricksx.annotations.view.Adapter
import com.dailystudio.devbricksx.annotations.viewmodel.ViewModel
import com.dailystudio.devbricksx.ui.AbsSingleLineViewHolder
import com.dailystudio.devbricksx.utils.ResourcesCompatUtils

@RoomCompanion(primaryKeys = ["uid"],
    autoGenerate = true,
    extension = User3DaoExtension::class
)
@ViewModel
@Adapter(viewHolder = User3ViewHolder::class,
    paged = true)
@ListFragment(dataSource = DataSource.Flow)
data class User3(val uid: Int,
                val firstName: String?,
                val lastName: String?)

@DaoExtension(entity = User3::class)
interface User3DaoExtension {
    @Query("SELECT * FROM user3 WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User3>

    @Insert
    fun insertAll(vararg users: User3)
}

class User3ViewHolder(itemView: View): AbsSingleLineViewHolder<User3>(itemView) {

    override fun getIcon(item: User3): Drawable? {
        return ResourcesCompatUtils.getDrawable(itemView.context,
            R.mipmap.ic_user)
    }

    override fun getText(item: User3): CharSequence? {
        return buildString {
            append(item.firstName)
            append(' ')
            append(item.lastName?.uppercase())
        }
    }

}