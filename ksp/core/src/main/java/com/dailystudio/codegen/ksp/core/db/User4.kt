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
    extension = User4DaoExtension::class
)
@ViewModel
@Adapter(viewHolder = User4ViewHolder::class,
    paged = true)
@ListFragment(dataSource = DataSource.Flow)
data class User4(val uid: Int,
                val firstName: String?,
                val lastName: String?)

@DaoExtension(entity = User4::class)
interface User4DaoExtension {
    @Query("SELECT * FROM user4 WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User4>

    @Insert
    fun insertAll(vararg users: User4)
}

class User4ViewHolder(itemView: View): AbsSingleLineViewHolder<User4>(itemView) {

    override fun getIcon(item: User4): Drawable? {
        return ResourcesCompatUtils.getDrawable(itemView.context,
            R.mipmap.ic_user)
    }

    override fun getText(item: User4): CharSequence? {
        return buildString {
            append(item.firstName)
            append(' ')
            append(item.lastName?.uppercase())
        }
    }

}