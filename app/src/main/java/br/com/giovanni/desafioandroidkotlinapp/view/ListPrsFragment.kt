package br.com.giovanni.desafioandroidkotlinapp.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.giovanni.desafioandroidkotlinapp.R

/**
 * A simple [Fragment] subclass.
 */
class ListPrsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_list_prs, container, false)
    }


}
