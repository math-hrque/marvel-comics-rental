import { postUser } from "../services/API";
import { updateUser } from "../services/API";

export default class User {
   
    constructor (private nome: string, private email: string, private senha: string, private telefone: string, private id: number, private foto: string) {
        
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.id = id;
        this.foto = foto;
    }
    
    public async cadastrar  () {
        await postUser(this);
    }

    public async atualizar() {  
        await updateUser(this);

    }
    
    get Nome(): string {
        return this.nome;
    }

    set Nome(nome){
        this.nome = nome;
    }


    get Email(): string {
        return this.email;
    }

    set Email(email) {
        this.email = email
    }

    get Senha(): string {
        return this.senha;
    }

    set Senha(senha) {
        this.senha = senha
    }

    get Telefone(): string {
        return  this.telefone;
    }

    set Telefone(telefone) {
        this.telefone = telefone;
    }   
}
