package com.ayyulest.sportdb.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ayyulest.sportdb.R
import com.ayyulest.sportdb.adapter.TeamAdapter
import com.ayyulest.sportdb.model.TeamsItem
import com.ayyulest.sportdb.interactor.ApiClient
import com.ayyulest.sportdb.interactor.ApiService
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * A simple [Fragment] subclass.
 *
 */
@Suppress("UNCHECKED_CAST")
class TeamSportFragment : Fragment() {
    private var items: ArrayList<TeamsItem> = arrayListOf()
    private lateinit var teamAdapter: TeamAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_team_sport, container, false)
        val recyclerView:RecyclerView = view.findViewById(R.id.rv_list)


        val service :ApiService = ApiClient.provideApi()
        service.getTeamByLigaId("4329")
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                items.clear()
                items = it.teams as ArrayList<TeamsItem>
                teamAdapter = TeamAdapter(activity, items)
                recyclerView.adapter = teamAdapter
                recyclerView.layoutManager = LinearLayoutManager(activity)

            })

        return view
    }


}
