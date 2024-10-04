package project.fitfusion.firebase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import project.fitfusion.R
import project.fitfusion.models.Food
import java.lang.reflect.Type

class FoodItemAdapter(context: Context, private val foodList: List<Food>) {
    class FoodViewHolder(itemView: View){
        val foodName: TextView = itemView.findViewById(R.id.tv_food_name)
        val timeStamp: TextView = itemView.findViewById(R.id.tv_time_stamp)
        val calories: TextView = itemView.findViewById(R.id.tv_calories)
        val protein: TextView = itemView.findViewById(R.id.tv_protein)
        val fats: TextView = itemView.findViewById(R.id.tv_fats)
        val carbs: TextView = itemView.findViewById(R.id.tv_carbs)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):FoodViewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return FoodViewHolder(itemView)
    }


}