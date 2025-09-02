import { useState, useEffect } from 'react';


function App() {
    const initPokemons = [
        { name: 'Pikachu', type: 'Électrik' },
        { name: 'Bulbizarre', type: 'Plante' },
        { name: 'Salamèche', type: 'Feu' },
        { name: 'Carapuce', type: 'Eau' },
    ];

    const [pokemons] = useState(initPokemons);
    const [searchTerm, setSearchTerm] = useState(() => localStorage.getItem('search') ?? 'Pikachu');
    
    useEffect(() => {
        localStorage.setItem('search', searchTerm);
    }, [searchTerm]);

     const handleSearch = (event) => setSearchTerm(event.target.value);

     const filteredPokemons = pokemons.filter((pokemon) => {
        const byName = pokemon.name.toLowerCase().includes(searchTerm.toLowerCase());
        return byName;
      });

    return (
        <>
            <h1>Poke-Search</h1>
            <h2>The fastest way to catch them all!</h2>
            <Search searchTerm={searchTerm} onSearch={handleSearch} />
            <hr />
            <PokemonList pokemons={filteredPokemons} />  
        </>
    );
}

const Search = ({ searchTerm, onSearch }) => (
    <input 
        type="text" 
        onChange={onSearch} 
        value={searchTerm} 
        placeholder="Search Pokemon..." 
    />
);

const PokemonList = ({ pokemons  }) => 
     (
        <ul>
            {pokemons.map((pokemon) => (
                <Pokemon key={pokemon.name} pokemonDetails={pokemon} />
            ))}
        </ul>
    );


const Pokemon = ({ pokemonDetails }) => 
    (
        <li key={pokemonDetails.name}>
            {pokemonDetails.name} - {pokemonDetails.type}
        </li>
    );


export default App;
