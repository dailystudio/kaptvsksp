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
    extension = User2DaoExtension::class
)
@ViewModel
@Adapter(viewHolder = User2ViewHolder::class,
    paged = true)
@ListFragment(dataSource = DataSource.Flow)
data class User2(val uid: Int,
                val firstName: String?,
                val lastName: String?)

@DaoExtension(entity = User2::class)
interface User2DaoExtension {
    @Query("SELECT * FROM user2 WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User2>

    @Insert
    fun insertAll(vararg users: User2)
}

class User2ViewHolder(itemView: View): AbsSingleLineViewHolder<User2>(itemView) {

    override fun getIcon(item: User2): Drawable? {
        return ResourcesCompatUtils.getDrawable(itemView.context,
            R.mipmap.ic_user)
    }

    override fun getText(item: User2): CharSequence? {
        return buildString {
            append(item.firstName)
            append(' ')
            append(item.lastName?.uppercase())
        }
    }

}