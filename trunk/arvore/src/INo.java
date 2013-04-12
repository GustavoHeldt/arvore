public interface INo {

    public abstract INo getFilhoDireito();

    public abstract INo getFilhoEsquerdo();

    public abstract INo getPai();

    public abstract Object getChave();

    public abstract void setFilhoDireito(INo fD);

    public abstract void setFilhoEsquerdo(INo fE);

    public abstract void setPai(INo pai);

    public abstract void setChave(Object ch);
    
    
    public abstract boolean isFolha();
    //corrigir os metodos isFolha e isExterno
    public abstract boolean isExterno();
    
    public abstract boolean isRaiz();

}