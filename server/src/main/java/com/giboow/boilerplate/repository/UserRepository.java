package com.giboow.boilerplate.repository;

import com.giboow.boilerplate.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
abstract public class UserRepository implements CrudRepository<User, Long> {


}
