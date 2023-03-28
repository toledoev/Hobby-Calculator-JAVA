
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class HobCalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int totalScore = 0;

		String name = request.getParameter("user");
		
		Map<String, Integer> scores = new HashMap<>();
		scores.put("running", 10);
		scores.put("swimming", 10);
		scores.put("cycling", 10);
		scores.put("football", 10);
		scores.put("soccer", 10);
		scores.put("rugby", 10);
		scores.put("pop", 10);
		scores.put("classical", 10);
		scores.put("country", 10);
		

		totalScore = scores.entrySet().stream()
		        .filter(e -> request.getParameter(e.getKey()) != null)
		        .mapToInt(Map.Entry::getValue)
		        .sum();


		String fitness = request.getParameter("fitness");
		if ("low".equals(fitness)) {
			totalScore += 10;
		} else if ("average".equals(fitness)) {
			totalScore += 20;
		} else if ("high".equals(fitness)) {
			totalScore += 30;
		}

		
		String reading = request.getParameter("reading");
		if (reading != null) {
			switch (reading) {
			case "HistoricalFiction":
				totalScore += 10;
				break;
			case "HistoricalNonFiction":
				totalScore += 20;
				break;
			case "CrimeFiction":
				totalScore += 30;
				break;
			case "Romance":
				totalScore += 10;
				break;
			case "Comedy":
				totalScore += 50;
				break;
			}
		}

		response.getWriter().append("Hello " + name.toUpperCase() + ", your total score is " + totalScore + ".");
	}

}
