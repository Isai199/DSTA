package app.dsta.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.dsta.databinding.ItemGamesrecentlyBinding
import org.json.JSONObject

class RecentlyGamesAdapter(private val games: Array<JSONObject>): RecyclerView.Adapter<RecentlyGamesAdapter.RecentlyGamesHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentlyGamesAdapter.RecentlyGamesHolder {
        val binding = ItemGamesrecentlyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentlyGamesHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentlyGamesAdapter.RecentlyGamesHolder, position: Int) {
        holder.render(games[position])
    }

    override fun getItemCount(): Int = games.size


    class RecentlyGamesHolder (val binding: ItemGamesrecentlyBinding): RecyclerView.ViewHolder(binding.root){
        fun render(game: JSONObject){

            binding.tvGamesTitle.setText(game.getString("title"))
            binding.tvGamesText1.setText(game.getString("textouno"))
            binding.tvGamesText2.setText(game.getString("textodos"))

        }

    }
}