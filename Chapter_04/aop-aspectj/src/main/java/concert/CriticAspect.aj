package concert;

public aspect CriticAspect {
    private CriticismEngine criticismEngine;

    pointcut performance():execution(* concert.Performance.perform(..));

    pointcut construct():execution(concert.CriticismEngineImpl.new());

    after():performance(){
        System.out.println(criticismEngine.getCriticism());
    }

    after():construct(){
        System.out.println("After Performance constructor");
    }


    before():construct(){
        System.out.println("Before Performance constructor");
    }

    public CriticismEngine getCriticismEngine() {
        return this.criticismEngine;
    }

    public void setCriticismEngine(CriticismEngine criticismEngine) {
        this.criticismEngine = criticismEngine;
    }
}
