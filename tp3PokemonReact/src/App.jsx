import { useState, useEffect } from 'react';
import Home from './pages/Home';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Navigation from './components/Navigation';
import PokemonDetailPage from './pages/PokemonDetailPage';
import FavoritesPage from './pages/FavoritesPage';
import TeamPage from './pages/TeamPage';
import ProfilePage from './pages/ProfilePage';
import NotFoundPage from './pages/NotFoundPage';

function App() {
    const [pokemons, setPokemons] = useState([]);
    // const [searchTerm, setSearchTerm] = useState(() => localStorage.getItem('search') ?? 'Pikachu');
    const [error, setError] = useState(null);

    // useEffect(() => {
    //     localStorage.setItem('search', searchTerm);
    // }, [searchTerm]);

    // const handleSearch = (event) => setSearchTerm(event.target.value);

    // const filteredPokemons = pokemons.filter((pokemon) => {
    //     const byName = pokemon.name.toLowerCase().includes(searchTerm.toLowerCase());
    //     return byName;
    // });

    return (
        <BrowserRouter>
            <div>
                <Navigation />
                <main>
                    <Routes>
                        <Route
                            path="/"
                            element={<Home Pokemons={pokemons} setPokemons={setPokemons} />}
                        />
                        <Route
                            path="/pokemon/:id"
                            element={<PokemonDetailPage pokemon={pokemons} />}
                        />
                        <Route path="/pokemon/favorites" element={<FavoritesPage />} />
                        <Route path="/profile/team" element={<TeamPage />} />
                        <Route path="/profile" element={<ProfilePage />} />
                        <Route path="*" element={<NotFoundPage />} />
                    </Routes>
                </main>
            </div>
        </BrowserRouter>

        // <div className="min-h-screen flex flex-col items-center py-10 bg-blue-900">
        //   <h1 className="text-6xl font-extrabold text-center bg-gradient-to-r from-blue-500 to-red-500 text-transparent bg-clip-text drop-shadow-[0_6px_6px_rgba(0,0,0,0.9)]">
        //     Poke-Search
        //   </h1>
        //   <h2 className="mt-3 text-2xl text-white drop-shadow-md">
        //     The fastest way to catch them all!
        //   </h2>

        //   <Search searchTerm={searchTerm} onSearch={handleSearch} />

        //   <PokemonList pokemons={filteredPokemons} />
        // </div>
    );
}

const Search = ({ searchTerm, onSearch }) => (
    <input
        type="text"
        onChange={onSearch}
        value={searchTerm}
        placeholder="Search Pokemon..."
        className="my-10 px-4 py-2 w-80 rounded-lg text-lg bg-white border-2 border-gray-300 text-gray-800 shadow-xl focus:outline-none focus:border-red-400 focus:ring-2 focus:ring-red-400"
    />
);

// const PokemonList = ({ pokemons }) => (
//     <ul className="mt-6 w-80 space-y-3 border-4 p-4 border-white/70 pt-4">
//         {pokemons.map((pokemon) => (
//             <Pokemon key={pokemon.name} pokemonDetails={pokemon} />
//         ))}
//     </ul>
// );

// const Pokemon = ({ pokemonDetails }) => (
//     <li className="bg-white rounded-lg px-4 py-2 shadow-md hover:shadow-xl transition text-gray-900">
//         <span className="font-semibold">{pokemonDetails.name}</span> – {pokemonDetails.type}
//     </li>
// );

export default App;
