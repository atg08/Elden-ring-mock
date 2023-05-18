package game.gameactors.enemies;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;

import java.util.List;

public interface IFollower {
    IFollowable getFollowingActor();
    IFollowable getANewActorToFollow(List<Exit> exits);
    void resetFollowingStatus();
    boolean canFollowActor(Actor actor);
    boolean isFollowingActorInExits(List<Exit> exits);

}
