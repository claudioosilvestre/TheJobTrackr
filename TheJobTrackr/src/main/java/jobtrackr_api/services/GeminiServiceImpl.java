package jobtrackr_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Service
public class GeminiServiceImpl implements GeminiService {

    private static final String PROMPT_TEMPLATE ="Act as a Senior Tech Recruiter / HR Business Partner. I need you to analyze the compatibility between a specific Candidate Profile (Job Title/Resume summary) and a Job Description. \n" +
            "\n" +
            "Please structure your response using the following sections:\n" +
            "1. Compatibility Score (Overall): An estimated percentage (e.g., 85%) based on how well the profile meets the job requirements.\n" +
            "2. Strong Matches (Strengths): Which core skills, experiences, or technologies in the profile align perfectly with the job description.\n" +
            "3. Skill Gaps (Weaknesses): Which mandatory or nice-to-have requirements from the job description are missing, weak, or unclear in the profile.\n" +
            "4. Verdict & Interview Questions: A brief conclusion on whether it is worth moving forward with this candidate, along with 2-3 specific questions to ask during an interview to clarify the identified gaps.\n" +
            "\n" +
            "Here is the data for your analysis:\n" +
            "\n" +
            "---\n" +
            "[CANDIDATE PROFILE / JOB TITLE]:\n" +
            "(Paste the candidate's resume summary, current title, key skills, and experience here)\n" +
            "\n" +
            "---\n" +
            "[JOB DESCRIPTION]:\n" +
            "(Paste the job responsibilities, mandatory requirements, and preferred skills here)\n" +
            "---";

    private RestClient restClient;

    @Value("${gemini.api.key}")
    private String apiKey;

    @Override
    public String analyzeCompatibility(String jobDescription, String position) {

        String prompt = PROMPT_TEMPLATE
                .replace("(Paste the candidate's resume summary, current title, key skills, and experience here)", position)
                .replace("(Paste the job responsibilities, mandatory requirements, and preferred skills here)", jobDescription);

        Map<String, Object> body = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", prompt)
                        ))
                )
        );

        String response = restClient.post()
                .uri("/v1beta/models/gemini-2.0-flash:generateContent?key=" + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .body(String.class);

        return response;
    }

    @Autowired
    public void setRestClient(RestClient restClient) {
        this.restClient = restClient;
    }
}
