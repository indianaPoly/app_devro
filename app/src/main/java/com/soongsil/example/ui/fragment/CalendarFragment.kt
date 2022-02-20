package com.soongsil.example.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.soongsil.example.ui.activity.Stopwatch
import com.soongsil.example.adapter.homeAdapter
import com.soongsil.example.databinding.FragmentCalendarBinding
import com.soongsil.example.model.Training
import com.soongsil.example.ui.dialog.MyCustomDialog
import com.soongsil.example.ui.dialog.MyCustomDialogInterface
import com.soongsil.example.viewmodel.TrainingViewModel

class CalendarFragment : Fragment(), MyCustomDialogInterface {

    private var binding : FragmentCalendarBinding? = null
    private val trainingViewModel: TrainingViewModel by viewModels()
    private val adapter : homeAdapter by lazy { homeAdapter(trainingViewModel) }

    private var year : Int = 0
    private var month : Int = 0
    private var day : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarBinding.inflate(inflater,container,false)
        adapter.setHasStableIds(true)
        binding!!.homeRv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        binding!!.homeRv.adapter = adapter
//        스톱워치 액티비티 추가할 것!
        binding!!.calendarview.setOnDateChangeListener { _, year, month, day ->
            this.year = year
            this.month = month + 1
            this.day = day
            trainingViewModel.readDateData(this.year,this.month,this.day).observe(viewLifecycleOwner
            , Observer {
                adapter.setData(it)
                })
        }
        binding!!.homeAddButton.setOnClickListener {
            if(year==0){
                Toast.makeText(requireActivity(),"날짜를 선택해주세요.", Toast.LENGTH_SHORT).show()
            }else{
                onFabOnClicked()
            }
        }
        binding!!.homeStopwatch.setOnClickListener {
//            fragment는 activity와 생명주기가 다르기에 넘겨주는 방식이 다르다.
            val intent = Intent(getActivity(), Stopwatch::class.java)
            startActivity(intent)
        }

        return binding!!.root
        }

    fun onFabOnClicked(){
        val myCustomDialog = MyCustomDialog(requireActivity(), this)
        myCustomDialog.show()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onOkButtonClicked(content: String){
        val training = Training(0,false,content,year,month,day)
        trainingViewModel.addTraining(training)
        Toast.makeText(activity,"운동을 추가했습니다.", Toast.LENGTH_SHORT).show()
    }
}