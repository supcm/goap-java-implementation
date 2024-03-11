package ru.supcm.level;

import ru.supcm.goap.*;
import ru.supcm.goap.actions.Action;
import ru.supcm.goap.actions.MoveToPlayerAction;
import ru.supcm.goap.actions.MoveToPosAction;
import ru.supcm.goap.goals.FollowPlayerGoal;
import ru.supcm.goap.goals.Goal;
import ru.supcm.goap.goals.WalkAroundGoal;
import ru.supcm.goap.targets.RandomPosTarget;

import java.awt.*;
import java.util.*;

public class Enemy extends Entity implements IGoapAgent<Enemy.State> {
    private static final Set<Action<State>> actions = new HashSet<>();
    private static final Set<Goal<State>> goals = new HashSet<>();
    static {
        actions.add(new MoveToPosAction());
        actions.add(new MoveToPlayerAction());

        goals.add(new WalkAroundGoal());
        goals.add(new FollowPlayerGoal());
    }
    private Target target;
    private final TargetSelector targetSelector;
    private final GoalSelector<State> goalSelector;
    private final ActionSelector<State> actionSelector;
    private State curState;
    private Goal<State> goal;
    Stack<Action<State>> actionsChain = new Stack<>();
    Action<State> curAction = null;
    public Enemy(Pos pos, Level level) {
        super(pos, level);
        target = null;
        targetSelector = new TargetSelector(new RandomPosTarget(), level.getPlayer());
        goalSelector = new GoalSelector<>(getGoals());
        actionSelector = new ActionSelector<>(getActions());
        curState = State.CALM;
        updateActions();
    }

    @Override
    public Target getCurrentTarget() {
        return target;
    }

    @Override
    public void setCurrentTarget(Target target) {
        this.target = target;
    }

    @Override
    public Collection<Action<State>> getActions() {
        return actions;
    }

    @Override
    public Collection<Goal<State>> getGoals() {
        return goals;
    }

    @Override
    public void setState(State curState) {
        this.curState = curState;
    }

    @Override
    public State getState() {
        return curState;
    }

    @Override
    public Color getColor() {
        return Color.RED;
    }

    @Override
    public void tick() {
        if(curAction != null && curAction.canPerform(target, this)) {
            curAction.performAction(target, this);
        } else {
            if(!actionsChain.empty()) {
                chooseTarget();
                curAction = actionsChain.pop();
            }
            else
                updateActions();
        }
    }

    private void chooseTarget() {
        target = targetSelector.getTargets().stream().toList().get((new Random()).nextInt(0, 2));
        targetSelector.updateTargets();
    }

    private void updateActions() {
        chooseTarget();
        goal = goalSelector.getGoal(curState, target);
        actionsChain = actionSelector.getActionChain(goal, getState(), this);
        if(!actionsChain.empty())
            curAction = actionsChain.pop();
    }
    public enum State {
        CALM, AGGRESSIVE, DISTURBED;
    }
}
