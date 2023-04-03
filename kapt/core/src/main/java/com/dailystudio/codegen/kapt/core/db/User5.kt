package com.dailystudio.codegen.kapt.core.db

import android.graphics.drawable.Drawable
import android.view.View
import androidx.room.Insert
import androidx.room.Query
import com.dailystudio.codegen.kapt.core.R
import com.dailystudio.devbricksx.annotations.*
import com.dailystudio.devbricksx.ui.AbsSingleLineViewHolder
import com.dailystudio.devbricksx.utils.ResourcesCompatUtils

@RoomCompanion(primaryKeys = ["uid"],
    autoGenerate = true,
    extension = User5DaoExtension::class
)
@ViewModel
@Adapter(viewHolder = User5ViewHolder::class,
    paged = true)
@ListFragment(dataSource = DataSource.Flow)
data class User5(val uid: Int,
                val firstName: String?,
                val lastName: String?)

@DaoExtension(entity = User5::class)
interface User5DaoExtension {
    @Query("SELECT * FROM user5 WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User5>

    @Insert
    fun insertAll(vararg users: User5)
}

class User5ViewHolder(itemView: View): AbsSingleLineViewHolder<User5>(itemView) {

    override fun getIcon(item: User5): Drawable? {
        return ResourcesCompatUtils.getDrawable(itemView.context,
            R.mipmap.ic_user)
    }

    override fun getText(item: User5): CharSequence? {
        return buildString {
            append(item.firstName)
            append(' ')
            append(item.lastName?.uppercase())
        }
    }

}