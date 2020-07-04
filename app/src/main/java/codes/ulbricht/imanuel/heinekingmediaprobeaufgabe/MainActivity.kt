package codes.ulbricht.imanuel.heinekingmediaprobeaufgabe

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import codes.ulbricht.imanuel.heinekingmediaprobeaufgabe.adapter.BackgroundsViewAdapter
import com.squareup.picasso.Picasso
import java.io.File


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: BackgroundsViewAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val PERMISSION_REQUEST = 1

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private fun displayRecyclerView() {
        val path = "${Environment.getExternalStorageDirectory()}/DCIM"
        val directory = File(path)
        val images = directory.listFiles()
        viewAdapter.images = images.toList()
        recyclerView.adapter = viewAdapter
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                displayRecyclerView()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = getString(R.string.backgrounds)

        viewManager = GridLayoutManager(this, 4)
        viewAdapter = BackgroundsViewAdapter(ArrayList())

        recyclerView = findViewById<RecyclerView>(R.id.backgrounds_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
            && ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                PERMISSION_REQUEST
            );
            return;
        }

        displayRecyclerView()
    }
}
