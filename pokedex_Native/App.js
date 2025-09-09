import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, ActivityIndicator, FlatList, SafeAreaView } from 'react-native';
import PokemonCard from './components/PokemonCard';
import { getAllPokemons } from './api/config';
import { useEffect,useState } from 'react';




export default function App() {
const [pokemons, setPokemons] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

useEffect(() => {
    const fetchPokemons = async () => {
      try {
        setLoading(true);
        const apiPokemons = await getAllPokemons(20, 520);
        setPokemons(apiPokemons);
      } catch (err) {
        console.error(err);
        setError("Erreur lors du chargement des pokémons");
      } finally {
        setLoading(false);
      }
    };

    fetchPokemons();
  }, []);

  if (loading) {
    return (
      <View style={styles.center}>
        <Text style={styles.statusText}>Chargement…</Text>
      </View>
    );
  }

  if (error) {
    return (
      <View style={styles.center}>
        <Text style={styles.statusText}>{error}</Text>
      </View>
    );
  }

   return (
    <SafeAreaView style={styles.container}>
      <Text style={styles.title}>Tous les Pokémons</Text>

      <FlatList
        data={pokemons}
        keyExtractor={(pok) => pok.id.toString()}
        renderItem={({ item }) => <PokemonCard pokemon={item} />}
        contentContainerStyle={styles.list}/>

      <StatusBar style="auto" />
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 2,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },

  statusText: { 
    marginTop: 28, 
    fontSize: 16,
  },
  
  h3: { 
    textAlign: 'center', 
    fontSize: 24, 
    fontWeight: '700', 
    marginVertical: 16 
  },

  list: { 
    paddingHorizontal: 12, 
    paddingBottom: 24, 
    gap: 12 
  },
});
