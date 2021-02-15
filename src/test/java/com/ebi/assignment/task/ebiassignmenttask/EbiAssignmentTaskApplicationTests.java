package com.ebi.assignment.task.ebiassignmenttask;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(JUnitPlatform.class)
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class EbiAssignmentTaskApplicationTests {

	String username = "maroof";
    String password = "testing123";
   
    @Autowired
	private MockMvc mockMvc;

	@Test
    public void getsAllPersons() throws Exception {
		 
        mockMvc.perform(MockMvcRequestBuilders.get("/api/persons")
        		.header("Authorization", "Bearer " +getToken())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
	
	@Test
    public void getPersons() throws Exception {
		
	    
        mockMvc.perform(MockMvcRequestBuilders.get("/api/person/1")
        		.header("Authorization", "Bearer " +getToken())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
	
	@Test
    public void getPersonsWithfirst_name() throws Exception {
		
        mockMvc.perform(MockMvcRequestBuilders.get("/api/persons/first_name/Muhammad")
        		.header("Authorization", "Bearer " +getToken())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
	
	@Test
    public void getPersonsWithlast_name() throws Exception {
	    
        mockMvc.perform(MockMvcRequestBuilders.get("/api/persons/last_name/Maroof")
        		.header("Authorization", "Bearer " +getToken())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
	
	@Test
    public void getPersonsWithNonExistancefirst_name() throws Exception {
		
        mockMvc.perform(MockMvcRequestBuilders.get("/api/persons/first_name/abcdefghi")
        		.header("Authorization", "Bearer " +getToken())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();
    }
	@Test
    public void getPersonsWithInvalidFirstName() throws Exception {
		
        mockMvc.perform(MockMvcRequestBuilders.get("/api/person/noname/muhammad")
        		.header("Authorization", "Bearer " +getToken())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }
	
	@Test
    public void getPersonsWithNoExistance() throws Exception {
		
		 
         mockMvc.perform(MockMvcRequestBuilders.get("/api/person/500")
        		.header("Authorization", "Bearer " +getToken())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();
    }
	
	@Test
	public void deletePerson() throws Exception{
		
		String body = "{\"person\": ["+
			    "{\"first_name\": \"Muhammad\","+
			      "\"last_name\": \"Maroof\","+
			      "\"age\": \"23\","+
			      "\"favourite_colour\": \"Gray\" } ]}";
		  mockMvc.perform(MockMvcRequestBuilders.delete("/api/persons")
				  .header("Authorization", "Bearer " +getToken())
		    		.accept(MediaType.APPLICATION_JSON)
		    		.contentType(MediaType.APPLICATION_JSON)
		            .content(body))
		            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
	}
	
	@Test
	public void savePerson() throws Exception{
		
		String body = "{\"person\": ["+
			    "{\"first_name\": \"Zafar\","+
			      "\"last_name\": \"Iqbal\","+
			      "\"age\": \"63\","+
			      "\"favourite_colour\": \"Red\" } ]}";
		  mockMvc.perform(MockMvcRequestBuilders.post("/api/persons")
				  .header("Authorization", "Bearer " +getToken())
		    		.accept(MediaType.APPLICATION_JSON)
		    		.contentType(MediaType.APPLICATION_JSON)
		            .content(body))
		            .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
	}
	
	@Test
	public void savePersonAll() throws Exception{
		
		String body = "{\"person\": ["+
			    "{\"first_name\": \"Zafar\","+
			      "\"last_name\": \"Iqbal\","+
			      "\"age\": \"63\","+
			      "\"favourite_colour\": \"Red\" },"+
			      "{\"first_name\": \"Twink\","+
			      "\"last_name\": \"Noor\","+
			      "\"age\": \"13\","+
			      "\"favourite_colour\": \"Brown\" }]}";
		  mockMvc.perform(MockMvcRequestBuilders.post("/api/persons")
				  .header("Authorization", "Bearer " +getToken())
		    		.accept(MediaType.APPLICATION_JSON)
		    		.contentType(MediaType.APPLICATION_JSON)
		            .content(body))
		            .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
	}
	private String getToken() throws Exception {
		 String body = "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";

		    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/authenticate")
		    		.accept(MediaType.APPLICATION_JSON)
		    		.contentType(MediaType.APPLICATION_JSON)
		            .content(body))
		            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		    
		    String response = result.getResponse().getContentAsString();
		    response = response.replace("{\"jwt\":\"", "");
		    String token = response.replace("\"}", "");
		    return token;
	}
    
}
