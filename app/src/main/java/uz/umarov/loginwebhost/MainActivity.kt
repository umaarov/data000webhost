package uz.umarov.loginwebhost

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request.Method
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import uz.umarov.loginwebhost.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var requestQueue: RequestQueue
    private var success: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestQueue = Volley.newRequestQueue(applicationContext)
        binding.save.setOnClickListener {
            sendData()
        }
    }

    private val sendUrl = "https://appdata12345.000webhostapp.com/test/getData.php"


    private fun sendData() {
        val request = StringRequest(Method.POST, sendUrl, Response.Listener<String> {

            fun onResponse(response: String) {
                try {
                    val jobj = JSONObject(response)
                    success = jobj.getInt("Success")
                    if (success == 1) {
                        Toast.makeText(this, jobj.getString("TAG_MESSAGE"), Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(this, jobj.getString("TAG_MESSAGE"), Toast.LENGTH_SHORT)
                            .show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "Error occure + $e", Toast.LENGTH_SHORT).show()
                }
            }
            Response.ErrorListener() {

                fun onErrorResponse(error: VolleyError) {
                    Toast.makeText(this, error.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }) {
            val params = HashMap<String, String>()
            params["username"] = binding.username.text.toString()
            params["password"] = binding.password.text.toString()
        }
        request.retryPolicy = DefaultRetryPolicy(100, 1, 1.0f)
        requestQueue.add(request)
    }


}