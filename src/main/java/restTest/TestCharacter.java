package restTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import rest.RestGet;
import restTest.Character.CharacterModels;
import restTest.Character.ResultLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TestCharacter {

    static String url= "https://rickandmortyapi.com/api/character";
    static List<String> listUrls= new ArrayList<>();

static ObjectMapper om;


    @BeforeClass
    public static void startData() throws JsonProcessingException {
        listUrls.add(url);
        om = new ObjectMapper();
        while (true){
            CharacterModels characterModels= om.readValue(RestGet.GetExchange(url),CharacterModels.class);
            if (!Objects.equals(characterModels.info.next,null)){
                listUrls.add(characterModels.info.next);
                url= characterModels.info.next;
            }
            else break;
        }
        for (String l:listUrls){
            System.out.println(l);
        }
    }

    @Test
    public void test() throws JsonProcessingException {
        for (String l:listUrls){
            CharacterModels model=om.readValue(RestGet.GetExchange(l),CharacterModels.class);
            for(ResultLog r:model.results){
                System.out.println("---------------------------------------");
                System.out.println(r);
            }
        }

    }

}
