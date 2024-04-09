/**
 * Copyright 2016 Erik Jhordan Rey.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.samplepeopleapp.viewmodel;

import android.view.View;
import com.example.samplepeopleapp.object.People;

public class PeopleDetailViewModel {

    private final People people;

    public PeopleDetailViewModel(People people) {
        this.people = people;
    }

    public String getFullUserName() {
        return people.getFullName();
    }

    public String getUserName() {
        return people.getLogin().getUserName();
    }

    public String getEmail() {
        return people.getMail();
    }

    public int getEmailVisibility() {
        return people.hasEmail() ? View.VISIBLE : View.GONE;
    }

    public String getCell() {
        return people.getCell();
    }

    public String getPictureProfile() {
        return people.getPicture().getLarge();
    }

    public String getAddress() {
        return people.getLocation().getStreet().getName()
                + " "
                + people.getLocation().getStreet().getNumber() + " "
                + people.getLocation().getCity()
                + " "
                + people.getLocation().getState();
    }

    public String getGender() {
        return people.getGender();
    }
}
