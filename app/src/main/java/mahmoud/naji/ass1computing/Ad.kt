package mahmoud.naji.ass1computing

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.firebase.firestore.core.View
import kotlinx.android.synthetic.main.add_data.view.*
import kotlinx.android.synthetic.main.list.view.*


class Ad(context: Context, datalist :List<Data>)
    : ArrayAdapter<Data>(context, 0, datalist ) {
    override fun getView(
        position: Int,
        convertView: android.view.View?,
        parent: ViewGroup
    ): android.view.View {
        val view = LayoutInflater.from(context).inflate(R.layout.list, parent, false)
        val data: Data?? = getItem(position)


            view.tvname.text = data?.name
            view.tvnum.text = data?.num
            view.tvaddress.text = data?.address




        return view


    }}



