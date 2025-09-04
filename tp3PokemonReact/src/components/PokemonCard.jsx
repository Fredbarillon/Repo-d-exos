import { Link } from 'react-router-dom';
import { getPokemonImg } from '../api/config';

function PokemonCard({ pokemon }) {
   return (
    <div className="bg-white shadow-md rounded-lg p-4 flex flex-col items-center hover:shadow-xl transition">
      <img
        src={getPokemonImg(pokemon.id)}
        alt={pokemon.name}
        className="w-24 h-24 object-contain mb-3"
      />
      <h3 className="capitalize text-lg font-semibold mb-2">{pokemon.name}</h3>
      <Link
        to={`/pokemon/${pokemon.id}`}
        className="mt-auto inline-block px-4 py-2 bg-blue-500 text-white text-sm rounded hover:bg-blue-600 transition"
      >
        Détails
      </Link>
    </div>
  );
}

export default PokemonCard;
