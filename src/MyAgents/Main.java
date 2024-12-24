package MyAgents;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Date;

public class Main extends Agent {
    @Override
    protected void setup() {


        // Adding a OneShotBehaviour to display the current date
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                System.out.println("Hello! My agent " + getLocalName() + " is ready.");

                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.setContent("Bojour , est ce que ca va ?");
                AID dest = new AID("Agent2", AID.ISLOCALNAME);
                msg.addReceiver(dest);
                send(msg);
            }
        });

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                Date d = new Date();
                System.out.println("Current Date is : " + d.toLocaleString());
                block(60000);
            }
        });
    }
}
