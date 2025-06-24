package dev.kofeychi.interactic.figura.api;

import dev.kofeychi.interactic.figura.api.task.BlockRendererAPI;
import dev.kofeychi.interactic.figura.api.task.ItemRendererAPI;
import dev.kofeychi.interactic.figura.api.task.RenderAPIContainer;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.docs.LuaMethodDoc;
import org.figuramc.figura.lua.docs.LuaMethodOverload;
import org.figuramc.figura.lua.docs.LuaTypeDoc;
import org.figuramc.figura.math.vector.FiguraVec2;
import org.figuramc.figura.math.vector.FiguraVec4;
import org.luaj.vm2.LuaError;

import java.util.HashMap;

@LuaWhitelist
@LuaTypeDoc(name = "TaskAPI", value = "task")
public class TaskAPI {
    public HashMap<String,RenderAPIContainer> tasks = new HashMap<>();

    public TaskAPI(DrawAPI parent) {
        this.parent = parent;
    }

    public DrawAPI parent;

    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {@LuaMethodOverload(
                    argumentTypes = {RenderAPIContainer.class},
                    argumentNames = {"obj"}
            ), @LuaMethodOverload(
                    argumentTypes = {String.class},
                    argumentNames = {"obj"}
            )
            },
            value = "task.getTask")
    public RenderAPIContainer getTask(Object obj) {
        if(obj instanceof RenderAPIContainer rapic){
            return tasks.get(rapic.name);
        } else if(obj instanceof String str){
            return tasks.get(str);
        } else {
            throw new LuaError("task.getTask() called with object of type " + obj.getClass() + "which was not found or never added.");
        }
    }
    @LuaWhitelist
    public BlockRendererAPI newBlockTask(String name) {
        var task = new BlockRendererAPI(parent,name);
        tasks.put(name,task);
        return task;
    }
    @LuaWhitelist
    public ItemRendererAPI newItemTask(String name) {
        var task = new ItemRendererAPI(parent,name);
        tasks.put(name,task);
        return task;
    }
    @LuaWhitelist
    public RenderAPIContainer setTask(String name,RenderAPIContainer task) {
        tasks.put(name,task);
        return task;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}