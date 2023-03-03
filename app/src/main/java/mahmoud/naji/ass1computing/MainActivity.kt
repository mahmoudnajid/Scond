package mahmoud.naji.ass1computing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_data.*
import kotlinx.android.synthetic.main.add_data.view.*

class MainActivity : AppCompatActivity() {
    val db = Firebase.firestore
    var list:ArrayList<Data>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //firbase

        list = ArrayList()

        floatingActionButton.setOnClickListener {
            val alert =AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.add_data,null)
            alert.setView(view)
            val alertD = alert.create()
            alertD.show()

            view.btnsave.setOnClickListener {
                var name = view.edtname.text.toString()
                var num = view.edtnum.text.toString()
                var address = view.edtaddress.text.toString()
                if(name.isNotEmpty() && num.isNotEmpty() && address.isNotEmpty()){
                    val data = hashMapOf(
                        "name" to name,
                        "number" to num,
                        "address" to address
                    )
                    db.collection("Data")
                        .add(data).addOnSuccessListener{ documentReference ->
                            Toast.makeText(applicationContext,"${documentReference.id}", Toast.LENGTH_LONG).show()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(applicationContext,"$e", Toast.LENGTH_LONG).show()


                        }
                    alertD.dismiss()
                }
                else{
                    Toast.makeText(this,"Empty", Toast.LENGTH_LONG).show()

                }

            }
            fun onStart(){
                list!!.clear()
                super.onStart()

                db.collection("Data")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            list!!.add(Data(edtname.text.toString(),edtnum.text.toString(),edtaddress.text.toString()))
                            Toast.makeText(applicationContext,"${document.data}", Toast.LENGTH_LONG).show()

                        }
                        val dataAdp = Ad(applicationContext, list!!)
                        list1.adapter = dataAdp

                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(applicationContext,"Error", Toast.LENGTH_LONG).show()
                    }



            }

            }







        }
    }