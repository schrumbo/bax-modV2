package bax.module;

import lombok.Getter;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Set;

/**
 * Manages all modules for this mod
 */
@Getter
public class ModuleManager {
    private final HashMap<Class<? extends Module>, Module> modules;

    /**
     * Constructor for Module Manager
     */
    public ModuleManager(){
        this.modules = new HashMap<>();
        register();
    }

    /**
     * gets a module lol
     * @param module gets this module
     * @return the wanted module
     */
    public Module getModule(Class<? extends Module>module){
        return modules.get(module);
    }

    /**
     * Gets a Module by name
     * @param name the name
     * @return the module
     */
    public Module getModule(String name){
        for (Module module : modules.values()){
            if (module.getName().equalsIgnoreCase(name)){
                return module;
            }
        }
        return null;
    }

    public Module[] getModules(Category category){
        return getModules().values().stream().filter(module -> module.getCategory() == category).toArray(Module[]::new);
    }


    /**
     * registers modules
     */
    public void register(){
        final Reflections refl = new Reflections("bax.module.impl");
        final Set<Class<? extends Module>> classes = refl.getSubTypesOf(Module.class);

        for (Class<? extends Module> c : classes){
            try{
                final Module module = c.newInstance();
                modules.put(c, module);
            }catch(InstantiationException | IllegalAccessException e){}
        }
    }

    /**
     * unregisters a Module
     */
    public void unregister(Module... module){
        for(Module mod : module){
            modules.remove(mod.getClass());
        }
    }
}
