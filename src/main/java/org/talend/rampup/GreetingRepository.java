package org.talend.rampup;

/**
 * Created by jphautin on 26/01/17.
 */
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GreetingRepository extends MongoRepository<Greeting, String> {

    public List<Greeting>  findByName(String name);

}
