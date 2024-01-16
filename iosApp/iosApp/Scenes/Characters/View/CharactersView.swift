//
//  CharactersView.swift
//  iosApp
//
//  Created by Raul Quispe on 15/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct CharactersView: View {
    @ObservedObject
    var viewModel: CharactersViewModel
    
    init() {
        _viewModel = ObservedObject(wrappedValue: CharactersViewModel(repository: CharactersRepository()))
    }
    var body: some View {
       
        HStack {
            if viewModel.charactersRequestState?.isSuccess() == true {
                List(viewModel.charactersRequestState?.getCharacters() ?? [], id: \.id) { element in
                    CharacterItemView(character: .constant(element))
                }
            } else {
                Text("no loaded characters")
            }
        }.task {
            await viewModel.fetchData()
        }
   
       
        
    }
}


#Preview {
    CharactersView()
}
