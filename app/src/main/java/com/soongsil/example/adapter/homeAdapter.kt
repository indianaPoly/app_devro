package com.soongsil.example.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soongsil.example.databinding.ItemHomeListBinding
import com.soongsil.example.model.Training
import com.soongsil.example.ui.dialog.UpdateDialog
import com.soongsil.example.ui.dialog.UpdateDialogInterface
import com.soongsil.example.viewmodel.TrainingViewModel

class homeAdapter(private val trainingViewModel: TrainingViewModel): RecyclerView.Adapter<homeAdapter.MyViewHolder>() {
    private var trainingList = emptyList<Training>()

    class MyViewHolder(private val binding: ItemHomeListBinding) :
        RecyclerView.ViewHolder(binding.root), UpdateDialogInterface {
        lateinit var training: Training
        lateinit var trainingViewModel: TrainingViewModel

        fun bind(currentTraining: Training, trainingViewModel: TrainingViewModel) {
            binding.training = currentTraining
            this.trainingViewModel = trainingViewModel

            binding.trainingCheckBox.setOnCheckedChangeListener { _, check ->
                if(check){
                    training = Training(currentTraining.id, true, currentTraining.content, currentTraining.year, currentTraining.month, currentTraining.day)
                    this.trainingViewModel.updateTraining(training)
                }
                else{
                    training = Training(currentTraining.id, false, currentTraining.content,currentTraining.year, currentTraining.month, currentTraining.day)
                    this.trainingViewModel.updateTraining(training)
                }
            }

            binding.deleteButton.setOnClickListener {
                trainingViewModel.deleteTraining(currentTraining)
            }

            binding.updateButton.setOnClickListener {
                training = currentTraining
                val myCustomDialog = UpdateDialog(binding.updateButton.context, this)
                myCustomDialog.show()
            }
        }
        override fun onOkButtonClicked(content: String) {
            val updateTraining = Training(training.id, training.check, content,training.year,training.month,training.day)
            trainingViewModel.updateTraining(updateTraining)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemHomeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(trainingList[position],trainingViewModel)
    }

    override fun getItemCount(): Int {
        return trainingList.size
    }

    fun setData(training : List<Training>){
        trainingList = training
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}
