package sparql.app.http;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.jena.query.QueryParseException;

import com.google.gson.Gson;

import spark.ModelAndView;
import spark.template.jade.JadeTemplateEngine;
import sparql.app.common.visualizers.DotVisualizer;

public class Server {
    public void start() {
    	port(80);
    	staticFiles.location("/main/resources/public");
    	
    	get("/", (request, response) -> {
    		Map<String, String> map = new HashMap<>();

            return this.render(map, "index");
    	});
    	
    	path("/api", () -> {
    		post("/queries", (request, response) -> {
    			response.type("application/json");

    			try {
        			DotVisualizer sqv = new DotVisualizer(request.body());
    				
        			Gson gson = new Gson();
        			String json = gson.toJson(sqv.visualize()); 
        		    return json;
    			} catch(QueryParseException e) {
        			response.status(400);
        			
    				ErrorResponse res = (ErrorResponse) new ErrorResponse("QueryParseError", e.getMessage());
    				
        			Gson gson = new Gson();
        			String json = gson.toJson(res); 
        		    return json;
    			} catch(Exception e) {
    				response.status(400);
        			
    				ErrorResponse res = (ErrorResponse) new ErrorResponse("Exception", e.getMessage());
    				
    				e.printStackTrace();
    				
        			Gson gson = new Gson();
        			String json = gson.toJson(res); 
        		    return json;
    			}
    		});
    	});
    }
    
    public String render(Map<String, String> model, String templatePath) {
        return new JadeTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
