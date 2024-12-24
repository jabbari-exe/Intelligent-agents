package MyAgents;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Date;
public class NewAgent extends Agent {
    protected void setup() {
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null){
                    String contenu = msg.getContent();
                    String sender = msg.getSender().getLocalName();
                    System.out.println("Message from : "+sender +"\n" + contenu);
                }else
                    block();
            }
        });

    }
}
