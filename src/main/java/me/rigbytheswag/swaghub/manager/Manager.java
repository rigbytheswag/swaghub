package me.rigbytheswag.swaghub.manager;

public class Manager {

    public final ManagerHandler managerHandler;

    protected Manager(ManagerHandler managerHandler) {
        this.managerHandler = managerHandler;
    }
}
