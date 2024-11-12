package seedu.duke.flashutils.types;

/**
 * Represents an individual flashcard with a question, answer and topic
 */
public class Card {
    private String question;
    private String answer;
    private String topic;

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.topic = "";
    }

    public Card(String question, String answer, String topic) {
        this.question = question;
        this.answer = answer;
        this.topic = topic;
    }

    public String getQuestion() {
        return question;
    }

    public String getTopic() {
        return topic;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String toWritableString() {
        return String.format("%1$s | %2$s | %3$s", question, answer, topic);
    }

    @Override
    public String toString() {
        if(topic==null || topic.isEmpty() || topic.equalsIgnoreCase("null")){
            return String.format("%1$s : %2$s", question, answer);
        }
        return String.format("%1$s : %2$s (Topic: %3$s)", question, answer, topic);
    }
}
