package MyAgents;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.ContainerController;
import jade.wrapper.AgentController;

public class LancerJADE {
    public static void main(String[] args) {
        Runtime rt = Runtime.instance();
        Profile p = new ProfileImpl();
        p.setParameter(Profile.MAIN_HOST, "localhost");
        p.setParameter(Profile.GUI, "false"); // Launches the JADE GUI

        ContainerController cc = rt.createMainContainer(p);

        try {
            // Create and start UserAgent
            AgentController userAgent2 = cc.createNewAgent("UserAgent2", "MyAgents.UserAgent", null);
            userAgent2.start();

            AgentController userAgent1 = cc.createNewAgent("UserAgent1", "MyAgents.UserAgent", null);
            userAgent1.start();

            // Create and start CalculAgent
            AgentController calculAgent = cc.createNewAgent("CalculAgent", "MyAgents.CalculAgent", null);
            calculAgent.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
