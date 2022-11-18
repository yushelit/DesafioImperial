package adaptadores

import Modelo.Nave
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
//import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioimperial.databinding.NavesViewBinding
import com.example.desafioimperial.R


class NavesAdaptadorRecycler(var naves : ArrayList<Nave>, var  context: Context) : RecyclerView.Adapter<NavesAdaptadorRecycler.ViewHolder>() {
    lateinit var binding: NavesViewBinding

    companion object {
        var seleccionado:Int = -1
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.itemView.setBackgroundColor(Color.parseColor("#FF00BCD4"));
        val item = naves.get(position)

        holder.bind(item, context, position, this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.naves_view,parent,false))
    }
    override fun getItemCount(): Int {
        return naves.size
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val matricula = view.findViewById(R.id.matricula) as TextView
        val tipo = view.findViewById(R.id.tipe) as TextView
        val fotaca = view.findViewById(R.id.imgFoto) as ImageView

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(nav: Nave, context: Context, pos: Int, miAdaptadorRecycler: NavesAdaptadorRecycler) {
            matricula.text = nav.matricula.toString()
            tipo.text = nav.tipo

            when(nav.imagen){
                "caza" ->{
                    val urli = "@drawable/"+nav.imagen
                    val imageResource: Int = context.getResources().getIdentifier(urli, null, context.getPackageName())
                    var res: Drawable = context.resources.getDrawable(imageResource)
                    fotaca.setImageDrawable(res)
                }
                "bombardero" ->{
                    val urli = "@drawable/"+nav.imagen
                    val imageResource: Int = context.getResources().getIdentifier(urli, null, context.getPackageName())
                    var res: Drawable = context.resources.getDrawable(imageResource)
                    fotaca.setImageDrawable(res)
                }
                "lanzadera" ->{
                    val urli = "@drawable/"+nav.imagen
                    val imageResource: Int = context.getResources().getIdentifier(urli, null, context.getPackageName())
                    var res: Drawable = context.resources.getDrawable(imageResource)
                    fotaca.setImageDrawable(res)
                }

            }

            if (pos == seleccionado) {
                with(matricula) {
                    this.setTextColor(resources.getColor(R.color.red))
                }
            }else {
                with(matricula) {
                    this.setTextColor(resources.getColor(R.color.black))
                }
            }
            itemView.setOnClickListener(View.OnClickListener {
                if (pos == seleccionado){
                    seleccionado = -1
                }
                else {
                    seleccionado = pos
                }
                miAdaptadorRecycler.notifyDataSetChanged()
            })
        }
    }
}