import axios from 'axios';

export const api = axios.create({
    baseURL: 'https://pokeapi.co/api/v2/',
    timeout: 10000,
    headers: {
        'Content-Type': 'application/json',
    },
});

export const getAllPokemons = async (limit = 20, offset = 0) => {
  const response = await api.get(`/pokemon?limit=${limit}&offset=${offset}`);
  return response.data.results.map((pok) => {
    const id = pok.url.split('/').filter(Boolean).pop();
    return { ...pok, id };
  });
};

export const getOnePokemon = (idOrName) => api.get(`/pokemon/${idOrName}`);
export const getPokemonImg = (id) =>
    `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png`;
