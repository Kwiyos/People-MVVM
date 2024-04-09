package com.example.samplepeopleapp.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.app.Application;

public class PeopleViewModelFactory implements ViewModelProvider.Factory {
    private Application application;
    public PeopleViewModelFactory(@NonNull Application application) {
        super();
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PeopleViewModel.class)) {
            return (T) new PeopleViewModel(application);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
