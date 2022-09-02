import React from 'react'

import { View, StyleSheet, Text } from 'react-native'

export function ColorBox({ hexColor, name }) {
  const colorStyle = {
    backgroundColor: hexColor,
  }

  return (
    <View style={[styles.box, colorStyle]}>
      <Text style={[styles.text, styles.cyan]}>
        {name}: {hexColor}
      </Text>
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
  },

  view: {
    alignItems: 'center',
  },

  box: {
    width: '100%',
    padding: 20,
    marginVertical: 10,
    marginHorizontal: 10,
  },

  text: {
    textAlign: 'center',
    color: '#fff',
  },
})
