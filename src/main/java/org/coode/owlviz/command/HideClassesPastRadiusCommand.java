package org.coode.owlviz.command;

import org.coode.owlviz.ui.ClassRadiusDialog;
import org.coode.owlviz.ui.OWLVizIcons;
import org.coode.owlviz.util.graph.controller.Controller;
import org.coode.owlviz.util.okcanceldialog.OKCancelDialog;
import org.protege.editor.owl.ui.view.OWLSelectionViewAction;
import org.semanticweb.owlapi.model.OWLClass;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Mar 5, 2004<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class HideClassesPastRadiusCommand extends OWLSelectionViewAction {



    private Controller assertedController;

    private Controller inferredController;

    private ClassRadiusDialog dlg;


    public HideClassesPastRadiusCommand(Controller assertedController,
                                        Controller inferredController) {
        super("Hide classes past radius", OWLVizIcons.getIcon(OWLVizIcons.HIDE_CLASSES_PAST_RADIUS_ICON));
        this.putValue(AbstractAction.SHORT_DESCRIPTION, "Hide classes past radius");
        this.assertedController = assertedController;
        this.inferredController = inferredController;
        dlg = new ClassRadiusDialog(null, false);
    }

    public void updateState() {
        setEnabled(true);
    }

    public void dispose() {
    }


    /**
     * Invoked when an action occurs.
     */
    public void actionPerformed(ActionEvent e) {
        Object selObj = assertedController.getGraphSelectionModel().getSelectedObject();
        if (selObj != null) {
            if (dlg.showDialog() == OKCancelDialog.OPTION_APPROVE) {
                int classRadius = dlg.getClassRadius();
                assertedController.getVisualisedObjectManager().hideObjects(selObj, classRadius, OWLClass.class);
                inferredController.getVisualisedObjectManager().hideObjects(selObj, classRadius, OWLClass.class);
            }
        }
    }
}

