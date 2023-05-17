package game.gameactors.enemies;

import edu.monash.fit2099.engine.actors.Actor;

public interface IFollower {
    Actor getFollowingActor();
    Actor getANewActorToFollow();
}
