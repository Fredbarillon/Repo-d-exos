import { getPokemonImg } from '../api/config';
import { View,Image, TouchableOpacity, Text, StyleSheet } from 'react-native';


function PokemonCard({ pokemon, onPress }) {
    
    return (
        <View style={styles.card}>
            <Image
                source={{uri:getPokemonImg(pokemon.id)}}
                style={styles.image}
                accessibilityLabel={`image de ${pokemon.name}`}
                
            />
            <Text style={styles.h3}>{pokemon.name} </Text>
            <TouchableOpacity
                style={styles.button}
                onPress={onPress}
            >
              <Text style={styles.buttonText}>Détails</Text>  
            </TouchableOpacity>
        </View>
    );
}

const styles = StyleSheet.create({
  card: {
    flex: 1,
    backgroundColor: '#f6f6f6',
    borderRadius: 12,
    padding: 12,
    alignItems: 'center',
    justifyContent: 'center',
    margin: 6,
  },

  image: { width: 80,
           height: 80, 
           resizeMode: 'contain', 
           marginBottom: 8 
        },
        
  h3: { 
          fontSize: 16,
          fontWeight: '700', 
          textTransform: 'capitalize', 
          marginBottom: 8 
        },

  button: {
    paddingHorizontal: 12,
    paddingVertical: 8,
    borderRadius: 8,
    backgroundColor: '#2563EB',
  },

  buttonText: { 
    color: '#fff', 
    fontWeight: '600' 
  },
});
export default PokemonCard;