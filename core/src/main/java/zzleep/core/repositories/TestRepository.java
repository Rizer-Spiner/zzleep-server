package zzleep.core.repositories;

import zzleep.core.models.TestModel;

import java.util.List;

public interface TestRepository {

    TestModel add(TestModel model);
    TestModel get(int id);
    List<TestModel> getAll();
    TestModel update(TestModel model);
    void delete(int id);

}
