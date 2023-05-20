package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.environments.siteoflostgrace.SiteOfLostGrace;

public class ActivateAction extends Action {

    private SiteOfLostGrace site;

    public ActivateAction(SiteOfLostGrace _site) {
        this.site = _site;
    }

    public static String LOST_GRACE_DISCOVERED =
                    "\n" +
                    "                                                                                                                                                                                                                                     \n" +
                    "                                                                                                                                                                                                                                     \n" +
                    "`7MMF'        .g8\"\"8q.    .M\"\"\"bgd MMP\"\"MM\"\"YMM       .g8\"\"\"bgd `7MM\"\"\"Mq.        db       .g8\"\"\"bgd `7MM\"\"\"YMM      `7MM\"\"\"Yb. `7MMF' .M\"\"\"bgd   .g8\"\"\"bgd   .g8\"\"8q.`7MMF'   `7MF'`7MM\"\"\"YMM  `7MM\"\"\"Mq.  `7MM\"\"\"YMM  `7MM\"\"\"Yb.   \n" +
                    "  MM        .dP'    `YM. ,MI    \"Y P'   MM   `7     .dP'     `M   MM   `MM.      ;MM:    .dP'     `M   MM    `7        MM    `Yb. MM  ,MI    \"Y .dP'     `M .dP'    `YM.`MA     ,V    MM    `7    MM   `MM.   MM    `7    MM    `Yb. \n" +
                    "  MM        dM'      `MM `MMb.          MM          dM'       `   MM   ,M9      ,V^MM.   dM'       `   MM   d          MM     `Mb MM  `MMb.     dM'       ` dM'      `MM VM:   ,V     MM   d      MM   ,M9    MM   d      MM     `Mb \n" +
                    "  MM        MM        MM   `YMMNq.      MM          MM            MMmmdM9      ,M  `MM   MM            MMmmMM          MM      MM MM    `YMMNq. MM          MM        MM  MM.  M'     MMmmMM      MMmmdM9     MMmmMM      MM      MM \n" +
                    "  MM      , MM.      ,MP .     `MM      MM          MM.    `7MMF' MM  YM.      AbmmmqMA  MM.           MM   Y  ,       MM     ,MP MM  .     `MM MM.         MM.      ,MP  `MM A'      MM   Y  ,   MM  YM.     MM   Y  ,   MM     ,MP \n" +
                    "  MM     ,M `Mb.    ,dP' Mb     dM      MM          `Mb.     MM   MM   `Mb.   A'     VML `Mb.     ,'   MM     ,M       MM    ,dP' MM  Mb     dM `Mb.     ,' `Mb.    ,dP'   :MM;       MM     ,M   MM   `Mb.   MM     ,M   MM    ,dP' \n" +
                    ".JMMmmmmMMM   `\"bmmd\"'   P\"Ybmmd\"     .JMML.          `\"bmmmdPY .JMML. .JMM..AMA.   .AMMA. `\"bmmmd'  .JMMmmmmMMM     .JMMmmmdP' .JMML.P\"Ybmmd\"    `\"bmmmd'    `\"bmmd\"'      VF      .JMMmmmmMMM .JMML. .JMM..JMMmmmmMMM .JMMmmmdP'   \n" +
                    "                                                                                                                                                                                                                                     \n" +
                    "                                                                                                                                                                                                                                     \n" +
                    "\n";

    @Override
    public String execute(Actor actor, GameMap map) {
        site.setActivated(true);
        return "The lost grace has been activated";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "can activate the lost grace";
    }
}
