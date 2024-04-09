/**
 * Copyright 2016 Erik Jhordan Rey. <p/> Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at <p/> http://www.apache.org/licenses/LICENSE-2.0 <p/> Unless required by
 * applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the License for the specific language governing permissions and limitations under the License.
 */

package com.example.samplepeopleapp.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import android.view.View;

import com.example.samplepeopleapp.R;
import com.example.samplepeopleapp.model.PeopleFactory;
import com.example.samplepeopleapp.model.PeopleResponse;
import com.example.samplepeopleapp.model.PeopleService;
import com.example.samplepeopleapp.object.People;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeopleViewModel extends AndroidViewModel {
    private Context context;
    private PeopleService peopleService;
    public MutableLiveData<List<People>> peopleList = new MutableLiveData<>();
    public MutableLiveData<Integer> peopleProgress = new MutableLiveData<>();
    public MutableLiveData<Integer> peopleRecycler = new MutableLiveData<>();
    public MutableLiveData<Integer> peopleLabel = new MutableLiveData<>();
    public MutableLiveData<String> messageLabel = new MutableLiveData<>();

    public PeopleViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        peopleService = PeopleFactory.create();
        peopleProgress.setValue(View.GONE);
        peopleRecycler.setValue(View.GONE);
        peopleLabel.setValue(View.GONE);
        messageLabel.setValue(context.getString(R.string.default_loading_people));
    }

    public void onClickFabLoad(View view) {
        initializeViews();
        fetchPeopleList();
    }

    //It is "public" to show an example of test
    public void initializeViews() {
        peopleLabel.setValue(View.GONE);
        peopleRecycler.setValue(View.GONE);
        peopleProgress.setValue(View.VISIBLE);
    }

    // NOTE: This method can return the rx observer and just subscribe to it in the activity or fragment,
    // an Activity or Fragment needn't to implement from the Observer java class
    // (it was my first approach to avoid RX in UI now we can use LiveData instead)
    private void fetchPeopleList() {
        peopleService.fetchPeople(10, "en").enqueue(new Callback<PeopleResponse>() {
            @Override
            public void onResponse(Call<PeopleResponse> call, Response<PeopleResponse> response) {
                assert response.body() != null;
                peopleList.setValue(response.body().getPeopleList());
                peopleProgress.setValue(View.GONE);
                peopleLabel.setValue(View.GONE);
                peopleRecycler.setValue(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<PeopleResponse> call, Throwable throwable) {
                messageLabel.setValue(context.getString(R.string.error_loading_people));
                peopleProgress.setValue(View.GONE);
                peopleLabel.setValue(View.VISIBLE);
                peopleRecycler.setValue(View.GONE);
                throwable.printStackTrace();
            }
        });
//        Disposable disposable = peopleService.fetchPeople(PeopleFactory.RANDOM_USER_URL)
//                .subscribeOn(peopleApplication.subscribeScheduler())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<PeopleResponse>() {
//                    @Override
//                    public void accept(PeopleResponse peopleResponse) {
//                        changePeopleDataSet(peopleResponse.getPeopleList());
//                        peopleProgress.set(View.GONE);
//                        peopleLabel.set(View.GONE);
//                        peopleRecycler.set(View.VISIBLE);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) {
//                        messageLabel.set(context.getString(R.string.error_loading_people));
//                        peopleProgress.set(View.GONE);
//                        peopleLabel.set(View.VISIBLE);
//                        peopleRecycler.set(View.GONE);
//                        throwable.printStackTrace();
//                    }
//                });
//
//        compositeDisposable.add(disposable);
    }

    public void reset() {
        context = null;
    }
}
