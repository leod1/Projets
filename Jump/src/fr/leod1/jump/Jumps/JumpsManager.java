package fr.leod1.jump.Jumps;

import fr.leod1.jump.Utils.JumpLoc;

import java.util.ArrayList;

public class JumpsManager {

    private ArrayList<JumpObj> jumpObjs = new ArrayList<>();

    public Integer numberOfJumps() {
        return jumpObjs.size();
    }

    public ArrayList<JumpObj> getJumpObjs() {
        return jumpObjs;
    }

    public JumpObj getJumpObj(String name) {
        for (JumpObj jumpObj : jumpObjs) {
            if (jumpObj.getName().equalsIgnoreCase(name)) {
                return jumpObj;
            }
        }
        return null;
    }

    public JumpObj getJumpObjFromStart(JumpLoc jumpLoc) {
        for (JumpObj jumpObj : jumpObjs) {
            if (jumpObj.getStart() == jumpLoc) {
                return jumpObj;
            }
        }
        return null;
    }

    public JumpObj getJumpObjFromFinish(JumpLoc jumpLoc) {
        for (JumpObj jumpObj : jumpObjs) {
            if (jumpObj.getFinish() == jumpLoc) {
                return jumpObj;
            }
        }
        return null;
    }

    public boolean isJumpObj(String name) {
        for (JumpObj jumpObj : jumpObjs) {
            if (jumpObj.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public void setJumpObjs(ArrayList<JumpObj> jumpObjs) {
        this.jumpObjs = jumpObjs;
    }

    public void addJumpObj(JumpObj jumpObj) {
        this.jumpObjs.add(jumpObj);
    }

    public void removeJupsObj(JumpObj jumpObj) {
        jumpObjs.remove(jumpObj);
    }

    public void removeJupsObj(String name) {
        jumpObjs.remove(getJumpObj(name));
    }

}
