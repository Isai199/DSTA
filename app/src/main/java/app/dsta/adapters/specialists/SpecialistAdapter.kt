package app.dsta.adapters.specialists

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import app.dsta.databinding.ItemSpecialistBinding
import app.dsta.models.users.ProfileViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import org.json.JSONObject

class SpecialistAdapter(private val specialits: Array<JSONObject>): RecyclerView.Adapter<SpecialistAdapter.MainHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SpecialistAdapter.MainHolder {

        val binding = ItemSpecialistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  SpecialistAdapter.MainHolder(binding)

    }

    override fun onBindViewHolder(holder: SpecialistAdapter.MainHolder, position: Int) {
        holder.render(specialits[position])
    }

    override fun getItemCount(): Int = specialits.size

    class MainHolder(val binding: ItemSpecialistBinding): RecyclerView.ViewHolder(binding.root){
        fun render(specialist: JSONObject){

             binding.tvSpecialistNames.setText("${specialist.getString("first_name")} ${specialist.getString("last_name")}")
            binding.tvSpecialistEmail.setText(specialist.getString("email"))
            binding.tvSpecialistPhone.setText("phone: "+specialist.getString("phone"))

            binding.tvSpecialistCity.setText("city: "+specialist.getString("city"))



            //colocar el avatar con el url antes del ?
            var str = specialist.getString("avatar")
            var delimiter = "?"
            val parts = str.split(delimiter)

            Log.d("partes",parts[0])

            val image = binding.ivAvatarSpecialist
            Picasso.get().load(parts[0]).into(image)


            //agregar epsialista a firebase

            binding.ivSpecialistFavorites.setOnClickListener {

                // initialising Firebase auth object
                val database = Firebase.database
                val myRef = database.reference




                myRef.child("user").child("${specialist.getString("iduser")}").child("specialist").child("1").child("id").setValue("${specialist.getString("id")}")
                myRef.child("user").child("${specialist.getString("iduser")}").child("specialist").child("1").child("first_name").setValue("${specialist.getString("first_name")}")

                myRef.child("user").child("${specialist.getString("iduser")}").child("specialist").child("1").child("last_name").setValue("${specialist.getString("last_name")}")

                myRef.child("user").child("${specialist.getString("iduser")}").child("specialist").child("1").child("email").setValue("${specialist.getString("email")}")

                myRef.child("user").child("${specialist.getString("iduser")}").child("specialist").child("1").child("phone").setValue("${specialist.getString("phone")}")

                myRef.child("user").child("${specialist.getString("iduser")}").child("specialist").child("1").child("city").setValue("${specialist.getString("city")}")

                myRef.child("user").child("${specialist.getString("iduser")}").child("specialist").child("1").child("avatar").setValue("${parts[0]}")



                //Toast.makeText(, "Logged user", Toast.LENGTH_SHORT).show()
            }


        }
    }


}

/*class MainAdapter(private val videos: Array<JSONObject>): RecyclerView.Adapter<MainAdapter.MainHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MainHolder {

        val binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainAdapter.MainHolder, position: Int) {
        holder.render(videos[position])
    }

    override fun getItemCount(): Int = videos.size

    class MainHolder(val binding: ItemVideoBinding): RecyclerView.ViewHolder(binding.root){
        fun render(video: JSONObject){

            binding.tvVideoTitle.setText(video.getString("title"))
            binding.tvChannelName.setText(video.getString("title"))
            binding.tvVideoViews.setText(video.getString("title"))
            binding.tvdateposted.setText(video.getString("title"))
            binding.tvVideoDuration.setText(video.getString("title"))
            binding.ivVideoThumbnail.setImageResource(R.drawable.video_placeholder)
            binding.ivAvatar.setImageResource(R.drawable.avatar_placeholder)

        }
    }
}*/