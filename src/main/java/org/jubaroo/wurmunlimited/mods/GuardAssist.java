package org.jubaroo.wurmunlimited.mods;

import com.wurmonline.server.creatures.Communicator;
import com.wurmonline.server.players.Player;
import org.gotti.wurmunlimited.modloader.interfaces.PlayerMessageListener;
import org.gotti.wurmunlimited.modloader.interfaces.WurmServerMod;

public class GuardAssist implements WurmServerMod, PlayerMessageListener {

    public boolean onPlayerMessage(Communicator communicator, String message) {
        final Player player = communicator.getPlayer();
        final boolean emote = message.startsWith("/me ");

        try {
            if (!emote && (message.equals("help") || message.contains("guard!") || message.contains("guards!") || message.contains("help guard") || message.contains("help guards"))) {
                player.callGuards();
                return true;
            }
        } catch (Throwable e) {
            player.getCommunicator().sendNormalServerMessage(String.format("Action error in class (%s). Error message: %s", GuardAssist.class.getName(), e.toString()));
            player.getCommunicator().sendNormalServerMessage("Please inform a GM by opening a ticket (using /support) and provide the message above. Thank you.");
            return false;
        }
        return false;
    }

    public String getVersion() {
        return "v1.0";
    }

}
