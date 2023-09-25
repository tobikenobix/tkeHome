import java.util.Scanner;

public class ChatCheapyT{

  private static String handleSilence(String input){
    if (input.isBlank())
      return "Dann sage ich aber auch nichts!";
    else
      return null;
  }

  private static String handleTooLong(String input){
    if (input.length()>50)
      return "Das ist mir zuviel zu lesen! Bitte kürzen Sie Ihre Anfrage!";
    else
      return null;

  }

  private static String handleExam(String input){
    if(input.equals("Was kommt in der Klausur dran?"))
      return "Die Klausur orientiert sich an den Praktika!";
    else
      return null;

  }

  private static String handleQuestion(String input){
    if(input.endsWith("?"))
      return "Tut mir leid, aber die ChatCheapyT-Server sind gerade ausgelastet! Schließen Sie bitte ein ChatCheapyT-Pro-Abo ab!";
    return null;

  }

  private static String handleExclamation(String input){
    if (input.contains("!")){
      if(input.toLowerCase().contains("bitte"))
        return "Als Antwort habe ich ein YouTube-Video generiert: https://youtu.be/dQw4w9WgXcQ";
      else
        return "Wie ist das Zauberwort?";
    }
    return null;

  }

  private static String handleChatGPT(String input){
    if(input.contains("ChatGPT")){
      return input.replace("ChatGPT", "ChatCheapyT");
    }
    return null;

  }

  private static String handleScream(String input){
    int countUpper=0;
    for(int i=0; i < input.length(); i++){
      if(Character.isUpperCase(input.charAt(i))) countUpper++;
    }
    if(countUpper>=input.length()/2)
      return "Bitte schreien Sie mich nicht an!";
    return null;

  }

  private static String handleReverse(String input){
    if(input.startsWith("Umdrehen:")){
      StringBuffer sb = new StringBuffer(input.split(":",2)[0]);
      return sb.reverse().toString();
    }
    return null;

  }

  private static String handleAdd(String input){
    if(input.toLowerCase().startsWith("addiere")){
      String[] nums =input.split(" ");
      if(nums.length >3) {
          return "Nur zwei Zahlen erlaubt";
      }
      return String.format("%s plus %s ist gleich %f! Take that, ChatGPT",nums[1],nums[2],Double.parseDouble(nums[1])+Double.parseDouble(nums[2]));
    }

    return null;

  }

  public static void main(String[] args) {

    Scanner input = new Scanner(System.in);

    String prompt = null;

    do {

      System.out.print("Prompt: ");
      prompt = input.nextLine(); 

      String answer = handleSilence(prompt);

      if (answer == null)
        answer = handleTooLong(prompt);

      if (answer == null)
        answer = handleExam(prompt);

      if (answer == null)
        answer = handleQuestion(prompt);

      if (answer == null)
        answer = handleExclamation(prompt);

      if (answer == null)
        answer = handleScream(prompt);

      if (answer == null)
        answer = handleReverse(prompt);

      if (answer == null)
        answer = handleAdd(prompt);

      if (answer == null)
        answer = handleChatGPT(prompt);

      if (prompt.equalsIgnoreCase("bye"))
        continue;

      if (answer != null)        
        System.out.println("ChatCheapyT: " + answer);
      else
        System.out.println("ChatCheapyT: Ich verstehe Sie leider nicht!");


    } while (!prompt.equalsIgnoreCase("bye"));

    System.out.println("Bye!");

    input.close();
    
  }





}
