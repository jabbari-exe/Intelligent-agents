package MyAgents;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class CalculAgent extends Agent {
    @Override
    protected void setup() {
        System.out.println("CalculAgent started.");
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage message = receive();
                if (message != null) {
                    // Parse the received content
                    String content = message.getContent();
                    String[] parts = content.split(" ");
                    double num1 = Double.parseDouble(parts[0]);
                    String operator = parts[1];
                    double num2 = Double.parseDouble(parts[2]);
                    double result = 0;

                    // Perform the calculation
                    switch (operator) {
                        case "+":
                            result = num1 + num2;
                            break;
                        case "-":
                            result = num1 - num2;
                            break;
                        case "*":
                            result = num1 * num2;
                            break;
                        case "/":
                            if (num2 != 0) {
                                result = num1 / num2;
                            } else {
                                sendErrorMessage(message, "Error: Division by zero.");
                                return;
                            }
                            break;
                        default:
                            sendErrorMessage(message, "Error: Unknown operator.");
                            return;
                    }

                    // Send the result back
                    ACLMessage reply = message.createReply();
                    reply.setPerformative(ACLMessage.INFORM);
                    reply.setContent(num1 +" "+operator+" "+num2+" = "+result);
                    send(reply);
                } else {
                    block();
                }
            }

            private void sendErrorMessage(ACLMessage message, String error) {
                ACLMessage reply = message.createReply();
                reply.setPerformative(ACLMessage.FAILURE);
                reply.setContent(error);
                send(reply);
            }
        });
    }
}
