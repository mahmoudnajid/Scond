package mahmoud.naji.ass1computing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mo7ammedtabasi.assignment_1.AdapterData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_data.view.*


class MainActivity : AppCompatActivity() {
    val db = Firebase.firestore
    val dataList = ArrayList<Data>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        getData()

        floatingActionButton.setOnClickListener {
            val alert = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.add_data, null)
            alert.setView(view)
            val alertD = alert.create()
            alertD.show()

            view.btnsave.setOnClickListener {
                var name = view.edtname.text.toString()
                var num = view.edtnum.text.toString()
                var address = view.edtaddress.text.toString()
                if (name.isNotEmpty() && num.isNotEmpty() && address.isNotEmpty()) {
                    val data = hashMapOf(
                        "name" to name,
                        "number" to num,
                        "address" to address
                    )
                    db.collection("DataUsers")
                        .add(data).addOnSuccessListener { documentReference ->
                            Toast.makeText(
                                applicationContext,
                                "${documentReference.id}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(applicationContext, "$e", Toast.LENGTH_LONG).show()


                        }
                    getData()
                    alertD.dismiss()
                } else {
                    Toast.makeText(this, "Empty", Toast.LENGTH_LONG).show()

                }

            }


        }

    }

    private fun getData() {
        dataList.clear()
        db.collection("DataUsers")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val name = document.getString("name").toString()
                    val phone = document.getString("number").toString()
                    val address = document.getString("address").toString()
                    val contact = Data( name, phone, address)
                    dataList.add(contact)
                }
                val adapter = AdapterData(this, dataList)
                progressBar.visibility =View.GONE
                recyclerView.adapter = adapter
                recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL))
                recyclerView.layoutManager = LinearLayoutManager(this)
            }
            .addOnFailureListener { exception ->
                Log.w("firest", "Error.", exception)
            }

    }


}
