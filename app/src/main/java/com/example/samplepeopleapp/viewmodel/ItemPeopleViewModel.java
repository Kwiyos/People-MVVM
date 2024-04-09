/**
 * Copyright 2016 Erik Jhordan Rey.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.samplepeopleapp.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.samplepeopleapp.object.People;
import com.example.samplepeopleapp.view.PeopleDetailActivity;

public class ItemPeopleViewModel extends ViewModel {

    private final Context context;
    private MutableLiveData<People> people = new MutableLiveData<>();

    public ItemPeopleViewModel(Context context, People people) {
        this.context = context;
        this.people.setValue(people);
    }

    public String getFullName() {
        People peopleValue = people.getValue();
        return peopleValue.getName().getTitle() + "." + peopleValue.getName().getFirst() + " " + peopleValue.getName().getLast();
    }

    public String getCell() {
        return people.getValue().getCell();
    }

    public String getMail() {
        return people.getValue().getMail();
    }

    public String getPictureProfile() {
        return people.getValue().getPicture().getMedium();
    }

    public void onItemClick(View view) {
        context.startActivity(PeopleDetailActivity.launchDetail(view.getContext(), people.getValue()));
    }
}
