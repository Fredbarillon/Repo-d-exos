import { useEffect, useState } from 'react';
import PokemonCard from '../components/PokemonCard';
import { getAllPokemons } from '../api/config';

function Home({ Pokemons, setPokemons }) {
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchPokemons = async () => {
            try {
                setLoading(true);
                const response = await getAllPokemons(20, 0);
                const apiPokemons = response.data.results;
                setPokemons(apiPokemons);
            } catch (err) {
                setError('Erreur lors du chargement des pokemons');
                console.error(err);
            } finally {
                setLoading(false);
            }
        };

        fetchPokemons();
    }, []);

    if (loading) return <div className="text-center py-6 text-lg">Chargement...</div>;
    if (error) return <div className="text-center py-6 text-red-500">{error}</div>;

    return (
        <div className="container mx-auto px-4 py-8">
            <h1 className="text-3xl font-bold text-center mb-6">Tous les Pokémons</h1>
            <div className="grid gap-6 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4">
                {Array.isArray(Pokemons) &&
                    Pokemons.map(({ name, url }) => {
                        const id = url.split('/').filter(Boolean).pop();
                        return <PokemonCard key={id} pokemon={{ id, name }} />;
                    })}
            </div>
        </div>
    );
}

export default Home;
